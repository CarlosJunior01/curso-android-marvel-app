package com.example.marvelapp.presentation.characters

import androidx.paging.PagingData
import com.example.core.usecase.GetCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import com.example.testing.MainCoroutineRule
import com.example.testing.model.CharacterFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Rule
import java.lang.RuntimeException

//@RunWith -> Runner do Mockito ao utilizar as anotações "mock" nas classe de teste, vai fazer com
// que os objetos sejam automaticamente criados ao rodar o teste.
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest {

    @get:Rule
    @ExperimentalCoroutinesApi
    var mainCoroutineRule = MainCoroutineRule()

    // Precisamos de TestCoroutineDispatcher para conseguirmos utilizar coroutines e flow em nossos testes
    // TestCoroutineDispatcher colocará toda execução em uma mesma Tread e nos dá o controle do tempo de
    // execução das corrotinas nos testes, permitindo executar imediatamente, pausar e reiniciar a execução.
    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    // 3ª Passo -> Mockar a instância do UseCase para passar como parâmetro no ViewModel()
    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    private val charactersFactory = CharacterFactory()

    private val pagingDataCharacters = PagingData.from(
        listOf(
            charactersFactory.create(CharacterFactory.Hero.ThreeDMan),
            charactersFactory.create(CharacterFactory.Hero.ABomb)
        )
    )

    //Testando o ViewModel
    // 1ª Passo -> Declarar a variável do ViewModel que queremos testar (nunca mockar quem você quer testar e validar)
    private lateinit var charactersViewModel: CharactersViewModel

    // 2ª Passo -> Criar função especial de configuração do teste. (Essa função será chamada antes de cada função de teste
    // ser executada para fazermos as inicializações necessárias com o objeto totalmete limpo).
    //Então primeiramente instanciamos nosso ViewModel.
    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        charactersViewModel = CharactersViewModel(getCharactersUseCase)
    }

    // 4ª Passo -> Criar o primeiro caso de teste
    @Test
    @ExperimentalCoroutinesApi
    fun `should validate the paging data object values when calling charactersPagingData`() = runBlockingTest {

        whenever(getCharactersUseCase.invoke(any())).thenReturn(
            flowOf(
                pagingDataCharacters
            )
        )

        val result = charactersViewModel.charactersPagingData("")

        assertEquals(1, result.count())
    }

    @Test(expected = RuntimeException::class)
    @ExperimentalCoroutinesApi
    fun `should throw an exception when the calling to the use case returns an exception`() =
        runBlockingTest {
            whenever(getCharactersUseCase.invoke(any()))
                .thenThrow(RuntimeException())

            charactersViewModel.charactersPagingData("")
        }
}