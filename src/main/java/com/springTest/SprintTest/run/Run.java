package com.springTest.SprintTest.run;

import jakarta.validation.constraints.NotEmpty;

public record Run(
        Integer id,
        @NotEmpty String title,
        @NotEmpty String email
        ){}