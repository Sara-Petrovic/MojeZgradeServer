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

import rs.ac.bg.fon.nprog.zgradeserver.operation.stambenazajednica.NadjiStambeneZajedniceOperacija;
import rs.ac.bg.fon.nprog.zgradeserver.repository.Repository;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.Mesto;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.StambenaZajednica;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.VlasnikPosebnogDela;


	@ExtendWith(MockitoExtension.class)
	@RunWith(MockitoJUnitRunner.class)
	public class NadjiVlasnikePosebnihDelovaOperacijaTest {

			private NadjiVlasnikePosebnihDelovaOperacija operacija;

			@Mock
			Repository mockedRepository;

			@Before
			public void setUp() throws Exception {
				operacija = new NadjiVlasnikePosebnihDelovaOperacija();
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
			public void testPreconditionsNotClassVlasnikPosebnogDela() {
				assertThrows(java.lang.Exception.class, () -> operacija.preconditions(new Mesto()));
			}

			@Test
			public void testExecuteOperation() throws Exception {

				List<VlasnikPosebnogDela> vlasnici = new ArrayList<VlasnikPosebnogDela>();
				vlasnici.add(new VlasnikPosebnogDela(1l, "Pera", "Peric", "14", 55, "pera@gmail.com", new StambenaZajednica())); 

				assertNotNull(mockedRepository);
				VlasnikPosebnogDela vlasnik = new VlasnikPosebnogDela();

				Mockito.when(mockedRepository.get(vlasnik)).thenReturn(vlasnici);
				operacija.setRepository(mockedRepository);

				operacija.executeOperation(vlasnik);
				List<VlasnikPosebnogDela> vlasnici1 = operacija.getVlasnici();

				// Check the result
				assertNotNull(vlasnici1);

				assertEquals(vlasnici	, vlasnici1);
				assertEquals(vlasnici.size(), vlasnici1.size());

			}
			

		}
