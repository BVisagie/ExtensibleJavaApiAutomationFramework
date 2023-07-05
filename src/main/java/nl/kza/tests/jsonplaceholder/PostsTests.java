package nl.kza.tests.jsonplaceholder;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import nl.kza.jsonplaceholder.model.Comment;
import nl.kza.jsonplaceholder.model.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nl.kza.jsonplaceholder.shared.JsonPlaceholderApiConstants.URI_PART_COMMENTS;
import static nl.kza.jsonplaceholder.shared.JsonPlaceholderApiConstants.URI_PART_POSTS;
import static nl.kza.shared.SharedConstants.SYSTEM_UNDER_TEST_JSON_PLACEHOLDER;
import static nl.kza.shared.SharedConstants.TEST_TYPE_COMPONENT;
import static nl.kza.shared.SharedSpecifications.generalRequestSpecNoHeaderNoBody;
import static nl.kza.shared.SharedSpecifications.generalStatusOkOnlyResponseSpec;
import static org.assertj.core.api.Assertions.assertThat;

@Tag(SYSTEM_UNDER_TEST_JSON_PLACEHOLDER)
@Tag(TEST_TYPE_COMPONENT)
@DisplayName(SYSTEM_UNDER_TEST_JSON_PLACEHOLDER)
class PostsTests extends JsonPlaceholderBaseApi {
    @Test
    @DisplayName("When a list of all posts is request a non empty list will be returned.")
    void getPosts() {

        Response postsResponse = RestAssured
                .given()
                .spec(generalRequestSpecNoHeaderNoBody())
                .get(URI_PART_POSTS)
                .then()
                .spec(generalStatusOkOnlyResponseSpec(1500))
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(postsResponse.asString());
        List<Post> postsList = jsonPath.getList("", Post.class);
        assertThat(postsList).withFailMessage("Posts list seems to be empty!").isNotEmpty();
    }

    @Test
    @DisplayName("When a request is made for a list of comments linked to a post id a list of comments with details will be returned.")
    void getPostComments() {

        int randomCommentId = 1;

        Response postCommentResponse = RestAssured
                .given()
                .spec(generalRequestSpecNoHeaderNoBody())
                .get(URI_PART_POSTS + "/" + randomCommentId + URI_PART_COMMENTS)
                .then()
                .spec(generalStatusOkOnlyResponseSpec(1500))
                .extract()
                .response();

        JsonPath jsonPath = new JsonPath(postCommentResponse.asString());
        List<Comment> postsList = jsonPath.getList("", Comment.class);
        assertThat(postsList).withFailMessage("Comments list seems to be empty!").isNotEmpty();
    }
}
