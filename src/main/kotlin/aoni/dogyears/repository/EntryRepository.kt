package aoni.dogyears.repository


import aoni.dogyears.entity.Entry
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface EntryRepository : Neo4jRepository<Entry, Long> {
    fun findByLocalDateBetween(startLocalDate: LocalDate, finishLocalDate: LocalDate):List<Entry>
}


