package aoni.dogyears.entity

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import java.time.LocalDate

@Node
data class Entry constructor(@Id @GeneratedValue val id: Long? = null, val localDate: LocalDate, val activity: String)