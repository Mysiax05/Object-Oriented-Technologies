package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;
import pl.edu.agh.school.persistence.PersistenceManager;

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
    }
}
