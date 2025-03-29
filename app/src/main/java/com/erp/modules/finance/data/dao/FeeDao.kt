package com.erp.modules.finance.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.erp.modules.finance.data.model.Fee
import com.erp.modules.finance.data.model.PaymentStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface FeeDao {
    @Query("SELECT * FROM fees ORDER BY dueDate DESC")
    fun getAllFees(): Flow<List<Fee>>
    
    @Query("SELECT * FROM fees WHERE id = :id")
    fun getFeeById(id: String): Flow<Fee>
    
    @Query("SELECT * FROM fees WHERE studentId = :studentId ORDER BY dueDate DESC")
    fun getFeesByStudent(studentId: String): Flow<List<Fee>>
    
    @Query("SELECT * FROM fees WHERE feeType = :feeType ORDER BY dueDate DESC")
    fun getFeesByType(feeType: String): Flow<List<Fee>>
    
    @Query("SELECT * FROM fees WHERE paymentStatus IN ('PENDING', 'PARTIAL', 'OVERDUE') ORDER BY dueDate ASC")
    fun getPendingFees(): Flow<List<Fee>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFee(fee: Fee)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFees(fees: List<Fee>)
    
    @Update
    suspend fun updateFee(fee: Fee)
    
    @Delete
    suspend fun deleteFee(fee: Fee)
} 