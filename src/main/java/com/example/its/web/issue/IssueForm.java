package com.example.its.web.issue;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class IssueForm {

    @NotBlank
    @Size(max=256)
    private String summary;

    @NotBlank
    @Size(max=256)
    private String description;

    public @NotBlank @Size(max = 256) String getSummary() {
        return summary;
    }

    public void setSummary(@NotBlank @Size(max = 256) String summary) {
        this.summary = summary;
    }

    public @NotBlank @Size(max = 256) String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank @Size(max = 256) String description) {
        this.description = description;
    }
}
