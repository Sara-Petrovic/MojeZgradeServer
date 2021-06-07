package rs.ac.bg.fon.nprog.zgradeserver.operation.korisnik;

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

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class LoginOperacijaTest {

		private LoginOperacija operacija;
		
		@Mock
	    Repository mockedRepository; 
		
		@Before
		public void setUp() throws Exception {
			operacija = new LoginOperacija();
		}

		@After
		public void tearDown() throws Exception {
			operacija= null;
		}

		@Test
		public void testPreconditionsNull() {
			assertThrows(java.lang.Exception.class,()->operacija.executeOperation(null));
		}

		@Test
		public void testPreconditionsNotClassKorisnik() {
			assertThrows(java.lang.Exception.class,()->operacija.executeOperation(new Mesto()));
		}
		@Test
		public void testExecuteOperation() throws Exception {
			
			List<Korisnik> korisnik = new ArrayList<Korisnik>() ;
			korisnik.add(new Korisnik(1l,"Pera","Peric","pera","pera")); //korisnik koji se vraca iz "baze"
			
			assertNotNull(mockedRepository);
			Korisnik k = new Korisnik();
			
	        Mockito.when(mockedRepository.get(k)).thenReturn(korisnik);
	        operacija.setRepository(mockedRepository);


	        operacija.executeOperation(k);
	        Korisnik korisnik1 = operacija.getKorisnik();

	        // Check the result
	        assertNotNull(korisnik1);
	        
	        System.out.println(korisnik1);
	        assertEquals(korisnik.get(0), korisnik1);

		}

	}

