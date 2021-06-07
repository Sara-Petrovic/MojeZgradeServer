package rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import rs.ac.bg.fon.nprog.zgradeserver.operation.korisnik.LoginOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class NadjiSedniceSkupstinaOperacijaTest {

	private NadjiSedniceSkupstinaOperacija operacija;

	@Mock
	Repository mockedRepository;

	@Before
	public void setUp() throws Exception {
		operacija = new NadjiSedniceSkupstinaOperacija();
	}

	@After
	public void tearDown() throws Exception {
		operacija = null;
	}

	@Test
	public void testPreconditionsNull() {
		assertThrows(java.lang.Exception.class, () -> operacija.preconditions(null));
	}

	@Test
	public void testPreconditionsNotClassSednicaSkupstine() {
		assertThrows(java.lang.Exception.class, () -> operacija.preconditions(new Mesto()));
	}

	@Test
	public void testExecuteOperation() throws Exception {

		List<SednicaSkupstine> sednice = new ArrayList<SednicaSkupstine>();
		sednice.add(new SednicaSkupstine(1l, new Date(), 5, "krov", null, null)); // korisnik koji se vraca iz "baze"

		assertNotNull(mockedRepository);
		SednicaSkupstine ss = new SednicaSkupstine();

		Mockito.when(mockedRepository.get(ss)).thenReturn(sednice);
		operacija.setRepository(mockedRepository);

		operacija.executeOperation(ss);
		List<SednicaSkupstine> sednice1 = operacija.getListaSednica();

		// Check the result
		assertNotNull(sednice1);

		assertEquals(sednice, sednice1);
		assertEquals(sednice.size(), sednice1.size());

	}
	

}
