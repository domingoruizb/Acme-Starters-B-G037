
package acme.features.inventor.invention;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.inventions.Invention;
import acme.entities.inventions.Part;

@Repository
public interface InventorInventionRepository extends AbstractRepository {

	@Query("SELECT i FROM Invention i WHERE i.inventor.id = :inventionId")
	Collection<Invention> findInventionsByInventorId(int inventionId);

	@Query("SELECT i FROM Invention i WHERE i.id = :id")
	Invention findInventionById(int id);

	@Query("SELECT p FROM Part p WHERE p.invention.id = :inventionId")
	Collection<Part> findPartsByInventionId(int inventionId);

	@Query("SELECT i FROM Invention i WHERE i.ticker = :ticker")
	Invention findInventionByTicker(String ticker);

	@Query("SELECT count(p) > 0 FROM Part p WHERE p.invention.id = :inventionId")
	Boolean existsByInventionId(int inventionId);

}
