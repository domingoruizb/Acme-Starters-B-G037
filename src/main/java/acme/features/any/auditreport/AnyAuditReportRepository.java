
package acme.features.any.auditreport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditReport;

@Repository
public interface AnyAuditReportRepository extends AbstractRepository {

	@Query("select r from AuditReport r")
	Collection<AuditReport> findAllAuditReports();

	@Query("select r from AuditReport r where r.id = :id")
	AuditReport findAuditReportById(int id);
}
