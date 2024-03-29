package org.grantharper.example.repository;

import org.grantharper.example.domain.Recipe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void shouldPersistRecipe() {
        Recipe recipe = testEntityManager.persistFlushFind(new Recipe("Tomato Sauce", "Grant"));

        assertThat(recipe).isNotNull();
        assertThat(recipe.getId()).isNotNull();
        assertThat(recipe.getTitle()).isEqualTo("Tomato Sauce");
    }
}
