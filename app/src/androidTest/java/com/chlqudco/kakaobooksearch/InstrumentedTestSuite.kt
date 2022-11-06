package com.chlqudco.kakaobooksearch

import com.chlqudco.kakaobooksearch.data.db.BookSearchDaoTest
import com.chlqudco.kakaobooksearch.ui.view.MainActivityTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@ExperimentalCoroutinesApi
@Suite.SuiteClasses(
    MainActivityTest::class,
    BookSearchDaoTest::class,
)
class InstrumentedTestSuite