
package acme.components;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.helpers.RandomHelper;
import acme.client.repositories.AbstractRepository;
import acme.entities.banners.Banner;

@Repository
public interface BannerRepository extends AbstractRepository {

	@Query("SELECT COUNT(b) FROM Banner b")
	Integer countBanners();

	@Query("SELECT b FROM Banner b")
	List<Banner> findAllBanners(PageRequest pageRequest);

	default Banner findRandomBanner() {
		Integer count = this.countBanners();
		if (count == 0)
			return null;

		Integer index = RandomHelper.nextInt(0, count);
		PageRequest page = PageRequest.of(index, 1, Sort.by(Direction.ASC, "id"));
		List<Banner> list = this.findAllBanners(page);
		return list.isEmpty() ? null : list.get(0);
	}

}
