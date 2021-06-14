package rs.ac.bg.fon.nprog.zgradeserver.operation.vlasnikposebnogdela;

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

import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.UcitajStambenuZajednicuOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;

	@ExtendWith(MockitoExtension.class)
	@RunWith(MockitoJUnitRunner.class)
	public class UcitajVlasnikaPosebnogDelaTest {

		private UcitajVlasnikaPosebnogDela operacija;
		
		@Mock
	    Repository mockedRepository; 
		
		@Before
		public void setUp() throws Exception {
			operacija = new UcitajVlasnikaPosebnogDela();
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
		public void testPreconditionsNotClassVlasnikPosebnogDela() {
			assertThrows(java.lang.Exception.class,()->operacija.preconditions(new Korisnik()));
		}
		@Test
		public void testExecuteOperation() throws Exception {
			// vlasnik kog vraca repository:
			List<VlasnikPosebnogDela> listaVlasnika = new ArrayList<VlasnikPosebnogDela>();
			listaVlasnika.add(new VlasnikPosebnogDela(1l, "Pera", "Peric", "14", 55, "pera@gmail.com", new StambenaZajednica()));
			
			assertNotNull(mockedRepository);
			
			VlasnikPosebnogDela pom = new VlasnikPosebnogDela();
	        Mockito.when(mockedRepository.get( pom)).thenReturn(listaVlasnika);
	        operacija.setRepository(mockedRepository);

	       operacija.executeOperation(pom);
	       VlasnikPosebnogDela vlasnik = operacija.getVlasnikPosebnogDela();

	        // Check the result
	        assertNotNull(vlasnik);
	        VlasnikPosebnogDela vlasnik1 = listaVlasnika.get(0);
	        
	        assertNotNull(vlasnik);
	        assertEquals(vlasnik, vlasnik1);

		}
		@Test
		public void testExecuteOperationNull() throws Exception {
			assertThrows(java.lang.Exception.class,()->operacija.executeOperation(null));

		}

	}
