package pl.edu.agh.school.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import pl.edu.agh.school.persistence.SerializablePersistenceManager;
import pl.edu.agh.school.persistence.SerializablePersistenceManagerInterface;

public class SchoolModule extends AbstractModule {

    @Provides
    SerializablePersistenceManagerInterface providePersistenceManager(SerializablePersistenceManager persistenceManager) {
        return  persistenceManager;
    }
}
