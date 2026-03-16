
package acme.features.auditor.auditreport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.audits.AuditReport;
import acme.entities.audits.AuditSection;

@Repository
public interface AuditorReportRepository extends AbstractRepository {

	@Query("select r from AuditReport r where r.id = :id")
	AuditReport findReportById(int id);

	@Query("select r from AuditReport r where r.auditor.id = :auditorId")
	Collection<AuditReport> findReportsByAuditorId(int auditorId);

	@Query("select s from AuditSection s where s.auditReport.id = :reportId")
	Collection<AuditSection> findSectionsByReportId(int reportId);

	@Query("SELECT r FROM AuditReport r WHERE r.ticker = :ticker")
	AuditReport findReportByTicker(String ticker);

	@Query("SELECT count(s) > 0 FROM AuditSection s WHERE s.auditReport.id = :reportId")
	Boolean existsByReportId(int reportId);

}
