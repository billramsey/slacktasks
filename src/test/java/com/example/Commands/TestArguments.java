package com.example.Commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class TestArguments {

  @Test
  public void testBlankText() {
    Arguments args = new Arguments("    ");
    assertNull(args.getCommand());
  }
  @Test
  public void testGetsCorrectCommand() {
    Arguments args = new Arguments("register bob now");
    assertEquals("register", args.getCommand());
    assertEquals(2, args.getArgs().length);
    assertEquals("bob", args.getArgs()[0]);
    assertEquals("now", args.getArgs()[1]);
  }

  @Test
  public void testArgumentsSizeValues() {
    Arguments args = new Arguments("register bob now");
    assertEquals(2, args.getArgs().length);
    assertEquals("bob", args.getArgs()[0]);
    assertEquals("now", args.getArgs()[1]);
  }

  @Test
  public void testArgumentsQuoted() {
    Arguments args = new Arguments("register \"bob now\"");
    assertEquals("bob now", args.getArgs()[0]);
  }

}
