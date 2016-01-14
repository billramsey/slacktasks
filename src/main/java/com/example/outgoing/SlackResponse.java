package com.example.outgoing;

import org.json.JSONObject;

public class SlackResponse {
  String username;
  String text;
  String channel;
  String icon_url;
  String icon_emoji;
  
  
  public SlackResponse(String text) {
    this.text = text;
  }
  
  
  public String toJSONString() {
    
    JSONObject result = new JSONObject();
    result.put("text", text);
    
    if (channel != null && !channel.equals("")) {
      result.put("channel", channel);
    }
    if (username != null && !username.equals("")) {
      result.put("username", username);
    }
    return result.toString();
  }


  public String getUsername() {
    return username;
  }


  public void setUsername(String username) {
    this.username = username;
  }


  public String getText() {
    return text;
  }


  public void setText(String text) {
    this.text = text;
  }


  public String getChannel() {
    return channel;
  }


  public void setChannel(String channel) {
    this.channel = channel;
  }


  public String getIcon_url() {
    return icon_url;
  }


  public void setIcon_url(String icon_url) {
    this.icon_url = icon_url;
  }


  public String getIcon_emoji() {
    return icon_emoji;
  }


  public void setIcon_emoji(String icon_emoji) {
    this.icon_emoji = icon_emoji;
  }
}
