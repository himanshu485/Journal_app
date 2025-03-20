package in.shiblinux.learnspeing.services;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import in.shiblinux.learnspeing.entity.User;

import java.util.ArrayList;
import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(User.builder().userName("Ram").passWord("Ram").build()),
                Arguments.of(User.builder().userName("Shyam").passWord("Shyam").build())
        );
    }
}
