package aoni.dogyears.entity

import org.springframework.data.neo4j.core.schema.GeneratedValue
import org.springframework.data.neo4j.core.schema.Id
import org.springframework.data.neo4j.core.schema.Node
import org.springframework.data.neo4j.core.schema.Relationship

@Node
data class Diary constructor(@Id @GeneratedValue val id: Long? = null, val name: String, @Relationship(type = "ENTRY") val entriesList: List<Entry>)