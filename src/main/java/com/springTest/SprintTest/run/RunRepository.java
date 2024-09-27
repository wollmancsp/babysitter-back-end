package com.springTest.SprintTest.run;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RunRepository {

    private static final Logger log = LoggerFactory.getLogger(RunRepository.class);
    private final JdbcClient jdbcClient;

    public RunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Run> findAll() {
        return jdbcClient.sql("select * from run").query(Run.class).list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT id,title, email FROM Run WHERE id = :id").param("id", id).query(Run.class).optional();
    }

    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id, title, email) values(?,?,?)")
                .params(List.of(run.id(),run.title(),run.email())).update();
        //Returns number of rows affected (want it to be 1)
        Assert.state(updated == 1, "Failed to create run " + run.title());
    }

    public void update(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE Run SET title = ? WHERE id = ?")
                .params(List.of(run.title(),id)).update();
        Assert.state(updated == 1, "Failed to update run " + run.title());
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Run WHERE id = :id")
                .param("id", id).update();
        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count() {return jdbcClient.sql("select * from run").query().listOfRows().size();}

    public void saveAll(List<Run> runs) {
        runs.stream().forEach(this::create);
    }

    public List<Run> findByTitle(String title) {
        return jdbcClient.sql("SELECT * FROM Run where title = :title")
                .param("title", title).query(Run.class).list();
    }

//    private List<Run> runs = new ArrayList();
//
//    List<Run> findAll() {
//        return runs;
//    }
//
//    Optional<Run> findById(Integer id) {
//        return runs.stream().filter(run -> run.id() == id).findFirst();
//    }
//
//    void create (Run run) {
//        runs.add(run);
//    }
//
//    void update (Run run, Integer id) {
//        Optional<Run> existingRun = findById(id);
//        if (existingRun.isPresent()) {
//            runs.set(runs.indexOf(existingRun.get()), run);
//        }
//    }
//
//    void delete (Integer id) {
//        runs.removeIf(run -> run.id().equals(id));
//    }
//
//    @PostConstruct
//    private void init() {
//        runs.add(new Run(1, "First Run"));
//        runs.add(new Run(2, "Second Run"));
//    }
}
