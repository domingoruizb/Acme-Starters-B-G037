
package acme.entities.audits;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;

@Repository
public interface AuditRepository extends AbstractRepository {

	@Query("SELECT sum(a.hours) FROM AuditSection a WHERE a.auditReport.id = :reportId")
	Integer findTotalNumberOfHoursBySection(int reportId);

	@Query("SELECT count(a) > 0 FROM AuditSection a WHERE a.auditReport.id = :reportId")
	Boolean existsByReportId(int reportId);

	@Query("select r from AuditReport r where r.ticker = :ticker")
	AuditReport findReportByTicker(String ticker);
}
