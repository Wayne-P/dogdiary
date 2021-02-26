package aoni.dogyears.repository


import aoni.dogyears.entity.Diary
import aoni.dogyears.entity.Entry
import org.springframework.data.neo4j.repository.Neo4jRepository
import org.springframework.data.neo4j.repository.query.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface DiaryRepository : Neo4jRepository<Diary, Long> {
    @Query(
        value = "MATCH (d:Diary) " +
                "WHERE id(d)=\$diaryId " +
                "WITH d " +
                "MATCH (e:Entry) " +
                "WHERE id(e)=\$entryId " +
                "CREATE (d)-[:ENTRY]->(e) "
    )
    fun addEntryToDiary(
        @Param("diaryId") diaryId: Long, @Param("entryId") entryId: Long
    )
}



