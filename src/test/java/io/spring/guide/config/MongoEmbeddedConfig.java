package io.spring.guide.config;

import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.config.DownloadConfigBuilder;
import de.flapdoodle.embed.mongo.config.ExtractedArtifactStoreBuilder;
import de.flapdoodle.embed.mongo.config.RuntimeConfigBuilder;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import io.spring.guide.repository.UserRepository;
import io.spring.guide.repository.sql.TransactionRepository;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;

/**
 * Config used to inject custom beans onto Spring Boot
 * to customize Embedded MongoDB configuration for EmbeddedMongoAutoConfig
 *
 * @see EmbeddedMongoProperties
 * @see org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
 *
 * @author Anton Kurako (anku0817)
 * @since 25.09.2017
 */
@Configuration
@AutoConfigureDataMongo
@EnableMongoAuditing
@EnableMongoRepositories(basePackageClasses = {
        UserRepository.class
})
public class MongoEmbeddedConfig {

    private static final int port = 0;
    private static final String database_name = "topology_manager";
    private static final String host = "localhost";

    private static final String mongodb_version = "3.2.2";
    private static final String mongodb_artifact_url = "https://artifactorycn.netcracker.com/nc.sandbox.files.dev/mongodb/";

    @Bean(destroyMethod = "close")
    public UserRepository userRepository() {
        return new MongoRepositoryFactoryBean<>(UserRepository.class).getObject();
    }

    @Bean(destroyMethod = "close")
    public TransactionRepository transactionRepository() {
        return new MongoRepositoryFactoryBean<>(TransactionRepository.class).getObject();
    }

    @Bean
    @Primary
    public EmbeddedMongoProperties embeddedMongoProperties() {
        EmbeddedMongoProperties embeddedMongoProperties = new EmbeddedMongoProperties();
        embeddedMongoProperties.setVersion(mongodb_version);
        return embeddedMongoProperties;
    }

    @Bean
    @Primary
    public MongoProperties mongoProperties() {
        MongoProperties props = new MongoProperties();

        if (port >= 0)
            props.setPort(port);

        props.setHost(host);
        props.setDatabase(database_name);
        return props;
    }

    // Uncomment if you want to choose custom source for embedded sql
//    @Bean
//    @Primary
    public IRuntimeConfig runtimeConfigDownload() {
        Command command = Command.MongoD;
        return new RuntimeConfigBuilder()
                .defaults(command)
                .artifactStore(new ExtractedArtifactStoreBuilder()
                        .defaults(command)
                        .download(new DownloadConfigBuilder()
                                .defaultsForCommand(command)
                                .downloadPath(mongodb_artifact_url).build()
                        )).build();
    }
}
