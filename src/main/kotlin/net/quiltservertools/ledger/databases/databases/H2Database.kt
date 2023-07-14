package net.quiltservertools.ledger.databases.databases

import com.github.quiltservertools.ledger.Ledger
import net.minecraft.server.MinecraftServer
import net.minecraft.util.WorldSavePath
import org.h2.jdbcx.JdbcDataSource
import org.jetbrains.exposed.sql.Database
import java.nio.file.Path
import javax.sql.DataSource

object H2Database : LedgerDatabase {
    override fun getDataSource(savePath: Path): DataSource {
        val db = JdbcDataSource()
        db.setURL("jdbc:h2:${savePath.resolve("ledger.h2").toFile()};MODE=MySQL")
        return db
    }

    override fun getDatabaseIdentifier() = Ledger.identifier("h2")
}
