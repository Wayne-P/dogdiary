package aoni.dogyears

import aoni.dogyears.entity.Diary
import aoni.dogyears.entity.Entry
import aoni.dogyears.repository.DiaryRepository
import aoni.dogyears.repository.EntryRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.time.LocalDate
import java.time.Month
import java.util.*

@SpringBootApplication
class DogyearsApplication {
    fun <T : Any> Optional<T>.asNonNullable(): T = this.orElseThrow()

    @Bean
    fun init(diaryRepository: DiaryRepository, entryRepository: EntryRepository): CommandLineRunner {
        return CommandLineRunner {
            val ld_2000Jan01: LocalDate = LocalDate.of(2000, Month.JANUARY, 1)
            val ld_2000Jan02: LocalDate = LocalDate.of(2000, Month.JANUARY, 2)
            val ld_2000Jan03: LocalDate = LocalDate.of(2000, Month.JANUARY, 3)
            val barkAtMailCarrier = "bark at mail carrier"
            val chaseSquirrel = "chase squirrel"
            val hideFromCat = "hide from cat"
            val entryJan01BarkAtMailCarrier = Entry(localDate = ld_2000Jan01, activity = barkAtMailCarrier)
            val entryJan01ChaseSquirrel = Entry(localDate = ld_2000Jan01, activity = chaseSquirrel)
            val entryJan02BarkAtMailCarrier = Entry(localDate = ld_2000Jan02, activity = barkAtMailCarrier)
            val entryJan02HideFromCat = Entry(localDate = ld_2000Jan02, activity = hideFromCat)
            val entryJan03BarkAtMailCarrier = Entry(localDate = ld_2000Jan03, activity = barkAtMailCarrier)
            val entryJan03ChaseSquirrel = Entry(localDate = ld_2000Jan03, activity = chaseSquirrel)
            diaryRepository.deleteAll()
            entryRepository.deleteAll()
            val diary = Diary(
                name="Rover",
                entriesList = listOf<Entry>
                    (entryJan01BarkAtMailCarrier, entryJan01ChaseSquirrel, entryJan02BarkAtMailCarrier,
                    entryJan02HideFromCat)
            )
            val savedDiary = diaryRepository.save<Diary>(diary)
            val diaryId = savedDiary.id

            // option 1
            val readDiary = diaryRepository.findById(diaryId!!).asNonNullable()
            val extendedDiary = readDiary.copy(entriesList = readDiary.entriesList.plus(entryJan03BarkAtMailCarrier))
            diaryRepository.save<Diary>(extendedDiary)

            // option 2
            val savedEntry= entryRepository.save(entryJan03ChaseSquirrel)
            diaryRepository.addEntryToDiary(diaryId = diaryId, entryId = savedEntry.id!!)


            print("complete")
        }
    }
}

fun main(args: Array<String>) {
    runApplication<DogyearsApplication>(*args)
}
