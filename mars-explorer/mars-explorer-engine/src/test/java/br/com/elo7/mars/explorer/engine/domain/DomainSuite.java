/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elo7.mars.explorer.engine.domain;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author pedrotoliveira
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({br.com.elo7.mars.explorer.engine.domain.surface.SurfaceSuite.class, br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerSuite.class, br.com.elo7.mars.explorer.engine.domain.action.ActionSuite.class, br.com.elo7.mars.explorer.engine.domain.validator.ValidatorSuite.class})
public class DomainSuite {
	
}
