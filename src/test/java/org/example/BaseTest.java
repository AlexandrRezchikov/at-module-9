package org.example;

import org.testng.annotations.BeforeMethod;
import static org.example.data.TestData.*;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        Specifications.installSpecification(Specifications.requestSpec(URI));
    }
}
