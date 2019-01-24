package com.toolinc.jokes;

import com.google.gson.stream.JsonWriter;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import static com.google.common.truth.Truth.assertThat;

/** Tests for class {@link Joke}. */
@RunWith(JUnit4.class)
public final class JokeTest {

  private static final String JSON = JsonUnmarshallerHelper.toString(JokeTest.class, "/joke.json");
  private static final JsonWriter JSON_WRITER =
      new JsonWriter(new PrintWriter(new ByteArrayOutputStream(10)));

  @Rule public final ExpectedException expectedException = ExpectedException.none();

  @Test
  public void shouldCreateJoke() throws IOException {
    Joke joke = Joke.builder().fromJson(JSON);
    assertThat(joke.category()).isEqualTo("Birthday Jokes");
    assertThat(joke.title()).isEqualTo("THE HUNTER’S BIRTHDAY");
    assertThat(joke.question()).isEqualTo("What do you get a hunter for his birthday?");
    assertThat(joke.answer()).isEqualTo("A birthday pheasant");
  }

  @Test
  public void shouldNotCreateJson() throws IOException {
    expectedException.expect(UnsupportedOperationException.class);
    expectedException.expectMessage("supported");
    Joke.builder().write(JSON_WRITER, Joke.builder().fromJson(JSON));
  }
}
