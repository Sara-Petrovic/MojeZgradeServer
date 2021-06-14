package rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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

import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;


	@ExtendWith(MockitoExtension.class)
	@RunWith(MockitoJUnitRunner.class)
	public class UcitajStambenuZajednicuOperacijaTest {

		private UcitajStambenuZajednicuOperacija operacija;
		
		@Mock
	    Repository mockedRepository; 
		
		@Before
		public void setUp() throws Exception {
			operacija = new UcitajStambenuZajednicuOperacija();
		}

		@After
		public void tearDown() throws Exception {
			operacija= null;
		}

		@Test
		public void testPreconditionsNull() {
			assertThrows(java.lang.Exception.class,()->operacija.preconditions(null));
		}

		@Test
		public void testPreconditionsNotClassStambenaZajednica() {
			assertThrows(java.lang.Exception.class,()->operacija.preconditions(new Korisnik()));
		}
		@Test
		public void testExecuteOperation() throws Exception {
			// stamb. zajednica koju vraca repository:
			List<StambenaZajednica> listaSZ = new ArrayList<StambenaZajednica>();
			listaSZ.add(new StambenaZajednica(1l, "Hilandarska", "4", new Mesto(), "170888", "Intesa","123","321"));
			
			assertNotNull(mockedRepository);
			
	        Mockito.when(mockedRepository.get( new StambenaZajednica())).thenReturn(listaSZ);
	        operacija.setRepository(mockedRepository);

	       operacija.executeOperation(new StambenaZajednica());
	       StambenaZajednica sz = operacija.getStambenaZajednica();

	        // Check the result
	        assertNotNull(sz);
	        StambenaZajednica stambenaZajednica = listaSZ.get(0);
	        
	        assertNotNull(stambenaZajednica);
	        assertEquals(sz, stambenaZajednica);

		}
		@Test
		public void testExecuteOperationNull() throws Exception {
			assertThrows(java.lang.Exception.class,()->operacija.executeOperation(null));

		}

	}

