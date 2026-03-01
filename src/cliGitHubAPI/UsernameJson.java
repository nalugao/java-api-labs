package cliGitHubAPI;

import com.google.gson.annotations.SerializedName;

public record UsernameJson(String login,
                           int id,
                           @SerializedName("node_id")
                           String nodeId,
                           @SerializedName("avatar_url")
                           String avatarUrl) {
}
