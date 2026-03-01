package cliGitHubAPI;

public class GitHubUserNotFoundException extends RuntimeException {
    private String message;

    public GitHubUserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
