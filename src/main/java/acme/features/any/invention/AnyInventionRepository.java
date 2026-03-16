
package acme.features.any.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;

@Repository
public interface AnyInventionRepository extends AbstractRepository {

	@Query("SELECT i FROM Invention i WHERE i.draftMode = FALSE")
	Collection<Invention> findPublishedInventions();

	@Query("SELECT i FROM Invention i WHERE i.id = :id")
	Invention findInventionById(int id);

}
