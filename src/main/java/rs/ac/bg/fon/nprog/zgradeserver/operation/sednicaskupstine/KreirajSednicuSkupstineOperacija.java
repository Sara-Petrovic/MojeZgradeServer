/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.nprog.zgradeserver.operation.sednicaskupstine;

import rs.ac.bg.fon.nprog.zgradeserver.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.zgradezajednicki.domain.SednicaSkupstine;

/**
 *
 * @author Sara
 */
public class KreirajSednicuSkupstineOperacija extends AbstractGenericOperation {

	SednicaSkupstine sednicaSkupstine;

	@Override
	protected void preconditions(Object param) throws Exception {
		if (param == null || !(param instanceof SednicaSkupstine)) {
			throw new Exception("Sistem ne moze da kreira sednicu skupstine");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		try {
			repository.add((SednicaSkupstine) param);
			this.sednicaSkupstine = (SednicaSkupstine) param; // promenili joj id u add
			System.out.println("id je " + sednicaSkupstine.getSednicaSkupstineId());
		} catch (Exception e) {
			throw new Exception("Sistem ne moze da kreira sednicu skupstine");
		}
	}

	public SednicaSkupstine getSednicaSkupstine() {
		return sednicaSkupstine;
	}

}
