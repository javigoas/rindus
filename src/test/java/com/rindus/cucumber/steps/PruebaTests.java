package com.rindus.cucumber.steps;

import com.rindus.configuration.TestConfiguration;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class PruebaTests extends TestConfiguration {
}
