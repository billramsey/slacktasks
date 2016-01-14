package com.example.outgoing;

public class SlackRequest {
  /*
 token=JTwIkNtiYOA0rJIFs5u74EuP
team_id=T0001
team_domain=example
channel_id=C2147483705
channel_name=test
user_id=U2147483697
user_name=Steve
command=/weather
text=94070
response_url=https://hooks.slack.com/commands/1234/5678
   */
  String token;
  String team_id;
  String channel_id;
  String channel_name;
  String user_id;
  String user_name;
  String command;
  String text;
  String response_url;

  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public String getTeam_id() {
    return team_id;
  }
  public void setTeam_id(String team_id) {
    this.team_id = team_id;
  }
  public String getChannel_id() {
    return channel_id;
  }
  public void setChannel_id(String channel_id) {
    this.channel_id = channel_id;
  }
  public String getChannel_name() {
    return channel_name;
  }
  public void setChannel_name(String channel_name) {
    this.channel_name = channel_name;
  }
  public String getUser_id() {
    return user_id;
  }
  public void setUser_id(String user_id) {
    this.user_id = user_id;
  }
  public String getUser_name() {
    return user_name;
  }
  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }
  public String getCommand() {
    return command;
  }
  public void setCommand(String command) {
    this.command = command;
  }
  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }
  public String getResponse_url() {
    return response_url;
  }
  public void setResponse_url(String response_url) {
    this.response_url = response_url;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("token: " + token + System.lineSeparator());
    sb.append("team_id: " + team_id + System.lineSeparator());
    sb.append("channel_id: " + channel_id + System.lineSeparator());
    sb.append("channel_name: " + channel_name + System.lineSeparator());
    sb.append("user_id: " + user_id + System.lineSeparator());
    sb.append("user_name: " + user_name + System.lineSeparator());
    sb.append("command: " + command + System.lineSeparator());
    sb.append("text: " + text + System.lineSeparator());
    sb.append("response_url: " + response_url + System.lineSeparator());


    return sb.toString();
  }


}

