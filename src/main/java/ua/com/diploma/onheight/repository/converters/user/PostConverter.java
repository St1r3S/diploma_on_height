package ua.com.diploma.onheight.repository.converters.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ua.com.diploma.onheight.model.user.Post;

@Converter(autoApply = true)
public class PostConverter implements AttributeConverter<Post, String> {

    @Override
    public String convertToDatabaseColumn(Post post) {
        if (post == null) {
            return null;
        }
        return post.getKey();
    }

    @Override
    public Post convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }
        return Post.get(s);
    }
}
