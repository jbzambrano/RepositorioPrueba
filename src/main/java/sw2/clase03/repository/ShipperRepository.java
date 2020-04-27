package sw2.clase03.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sw2.clase03.entity.Shipper;


import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {
    List<Shipper> findByCompanyName(String companyName);

    @Query(value="select * from shippers where CompanyName= ?1", nativeQuery=true)
    List<Shipper> buscarTransportistasPorNombre(String name);
}

