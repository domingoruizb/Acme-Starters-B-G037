
package acme.features.any.inventor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;
import acme.realms.Inventor;

@Repository
public interface AnyInventorRepository extends AbstractRepository {

	@Query("select i from Invention i where i.id = :inventionId")
	Invention findInventionById(int inventionId);

	@Query("select i.inventor from Invention i where i.id = :inventionId")
	Inventor findInventorByInventionId(int inventionId);

}
