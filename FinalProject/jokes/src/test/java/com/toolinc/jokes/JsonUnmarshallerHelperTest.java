package com.toolinc.jokes;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

/** Tests for class {@link JsonUnmarshallerHelper}. */
public final class JsonUnmarshallerHelperTest {

  @Rule public final ExpectedException expectedException = ExpectedException.none();

  @Test
  public void shouldReadFileContent() throws IOException {
    String file =
        JsonUnmarshallerHelper.toString(JsonUnmarshallerHelperTest.class, "/testFile.json");
    assertThat(file).isEqualTo("Hello World!!!!");
  }

  @Test
  public void shouldNotFindFileThrowsNullPointerException() throws IOException {
    expectedException.expect(NullPointerException.class);
    JsonUnmarshallerHelper.toString(JsonUnmarshallerHelperTest.class, "/doesNotExist.json");
  }

  @Test
  public void shouldUnmarshallJokeInstance() throws IOException {
    Joke joke = JsonUnmarshallerHelper.toJoke(JsonUnmarshallerHelperTest.class, "/joke.json");
    assertThat(joke).isNotNull();
  }
}
