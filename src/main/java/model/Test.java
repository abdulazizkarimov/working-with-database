package model;

import java.util.Objects;

public class Test {
    private int id;
    private String name;
    private String time;
    private int status;
    private int authorId;
    private int projectId;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getStatus() {
        return status;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Test(String name, String time, int status, int authorId, int projectId) {
        this.name = name;
        this.time = time;
        this.status = status;
        this.authorId = authorId;
        this.projectId = projectId;
    }

    public Test(int id, String name, String time, int status, int authorId, int projectId) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.status = status;
        this.authorId = authorId;
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id && status == test.status && authorId == test.authorId && projectId == test.projectId && Objects.equals(name, test.name) && Objects.equals(time, test.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, time, status, authorId, projectId);
    }
}