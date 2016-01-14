package com.example.outgoing;

import java.util.LinkedList;
import java.util.List;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import flowctrl.integration.slack.exception.SlackException;
import flowctrl.integration.slack.type.Attachment;


@JsonInclude(Include.NON_EMPTY)
public class SlackResponse {
  String username;
  String text;
  String channel;
  String icon_url;
  String icon_emoji;

  List<Attachment> attachments = new LinkedList<Attachment>();
  
  
  public SlackResponse(String text) {
    this.text = text;
  }

  public String toJSONString() {
    /*
    JSONObject result = new JSONObject();
    result.put("text", text);

    if (channel != null && !channel.equals("")) {
      result.put("channel", channel);
    }
    if (username != null && !username.equals("")) {
      result.put("username", username);
    }
    return result.toString();
    */
    ObjectMapper mapper = new ObjectMapper();
    
    String message = null;
    try {
        message = mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
        throw new SlackException(e);
    }
    
    return message;
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

  public List<Attachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<Attachment> attachments) {
    this.attachments = attachments;
  }
  public void addAttachment(Attachment attachment) {
    this.attachments.add(attachment);
  }
}
