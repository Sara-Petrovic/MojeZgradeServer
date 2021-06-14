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

import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.UcitajListuStambenihZajednicaOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Korisnik;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;


	@ExtendWith(MockitoExtension.class)
	@RunWith(MockitoJUnitRunner.class)
	public class UcitajListuVlasnikaPosebnihDelovaOperacijaTest {

		private UcitajListuVlasnikaPosebnihDelovaOperacija operacija;
		
		@Mock
	    Repository mockedRepository; 
		
		@Before
		public void setUp() throws Exception {
			operacija = new UcitajListuVlasnikaPosebnihDelovaOperacija();
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
			//lista vlasnika koju vraca repository:
			List<VlasnikPosebnogDela> vlasnici = new ArrayList<VlasnikPosebnogDela>();
			vlasnici.add(new VlasnikPosebnogDela(1l, "Pera", "Peric", "14", 55, "pera@gmail.com", new StambenaZajednica())); 
			
			assertNotNull(mockedRepository);
			
			VlasnikPosebnogDela pom = new VlasnikPosebnogDela();
	        Mockito.when(mockedRepository.getAll( pom)).thenReturn(vlasnici);
	        operacija.setRepository(mockedRepository);

	       operacija.executeOperation(pom);
	       List<VlasnikPosebnogDela> vlasnici1 = operacija.getVlasnici();

	        // Check the result
	        assertNotNull(vlasnici1);
	        VlasnikPosebnogDela vlasnik = vlasnici1.get(0);
	        
	        assertNotNull(vlasnik);
	        assertEquals(vlasnici.get(0), vlasnik);
	        assertEquals(1, vlasnici1.size());
		}
		@Test
		public void testExecuteOperationNull() throws Exception {
			assertThrows(java.lang.Exception.class,()->operacija.executeOperation(null));

		}

	}
