package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import pl.edu.agh.logger.ConsoleMessageSerializer;
import pl.edu.agh.logger.FileMessageSerializer;
import pl.edu.agh.logger.IMessageSerializer;
import pl.edu.agh.logger.Logger;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;
import pl.edu.agh.school.persistence.PersistenceManager;

import java.util.List;

public class SchoolModule extends AbstractModule {

    @Provides
    PersistenceManager providePersistenceManager(SerializablePersistenceManager persistenceManager) {
        return  persistenceManager;
    }

    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("teachersFile"))
                .toInstance("guice-teachers.dat");

        bind(String.class)
                .annotatedWith(Names.named("classesFile"))
                .toInstance("guice-classes.dat");

        bind(Logger.class).in(Singleton.class);
    }

    @Provides
    @Named("serializers")
    static List<IMessageSerializer> provideSerializer() {
        return List.of(new FileMessageSerializer("persistance.log"), new ConsoleMessageSerializer());
    }
}
