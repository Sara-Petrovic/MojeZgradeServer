package rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica;

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

import rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine.NadjiSedniceSkupstinaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class NadjiStambeneZajedniceOperacijaTest {

		private NadjiStambeneZajedniceOperacija operacija;

		@Mock
		Repository mockedRepository;

		@Before
		public void setUp() throws Exception {
			operacija = new NadjiStambeneZajedniceOperacija();
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
		public void testPreconditionsNotClassStambenaZajednica() {
			assertThrows(java.lang.Exception.class, () -> operacija.preconditions(new Mesto()));
		}

		@Test
		public void testExecuteOperation() throws Exception {

			List<StambenaZajednica> zajednice = new ArrayList<StambenaZajednica>();
			zajednice.add(new StambenaZajednica(1l, "Hilandarska", "4", new Mesto(), "170888", "Intesa","123","321")); 

			assertNotNull(mockedRepository);
			StambenaZajednica sz = new StambenaZajednica();

			Mockito.when(mockedRepository.get(sz)).thenReturn(zajednice);
			operacija.setRepository(mockedRepository);

			operacija.executeOperation(sz);
			List<StambenaZajednica> zajednice1 = operacija.getStambeneZajednice();

			// Check the result
			assertNotNull(zajednice1);

			assertEquals(zajednice, zajednice1);
			assertEquals(zajednice.size(), zajednice1.size());

		}
		

	}
