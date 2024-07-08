package com.example.payment

class TransactionAdapter(private val transactions: List<Transaction>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_SUMMARY = 0
        private const val VIEW_TYPE_TRANSACTION = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_SUMMARY -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_summary, parent, false)
                SummaryViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_transaction, parent, false)
                TransactionViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SummaryViewHolder -> bindSummary(holder)
            is TransactionViewHolder -> bindTransaction(holder, position - 1)
        }
    }

    override fun getItemCount(): Int = transactions.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_SUMMARY else VIEW_TYPE_TRANSACTION
    }

    private fun bindSummary(holder: SummaryViewHolder) {
        val moneyIn = transactions.filter { it.isIncome }.sumOf { it.amount }
        val moneyOut = transactions.filter { !it.isIncome }.sumOf { it.amount }
        val balance = moneyIn - moneyOut

        holder.moneyInText.text = "MONEY IN\nKES $moneyIn"
        holder.moneyOutText.text = "MONEY OUT\nKES $moneyOut"
        holder.balanceText.text = "BALANCE\nKES $balance"
        holder.transactionsText.text = "TRANSACTIONS\n${transactions.size}"
    }

    private fun bindTransaction(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        holder.transactionName.text = transaction.name
        holder.transactionDate.text = transaction.date
        holder.transactionAmount.text = "KES ${transaction.amount}"
        holder.transactionAmount.setTextColor(
            if (transaction.isIncome) Color.GREEN else Color.RED
        )
        // Set appropriate icon based on transaction type
    }

    class SummaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moneyInText: TextView = itemView.findViewById(R.id.moneyInText)
        val moneyOutText: TextView = itemView.findViewById(R.id.moneyOutText)
        val balanceText: TextView = itemView.findViewById(R.id.balanceText)
        val transactionsText: TextView = itemView.findViewById(R.id.transactionsText)
    }

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transactionIcon: ImageView = itemView.findViewById(R.id.transactionIcon)
        val transactionName: TextView = itemView.findViewById(R.id.transactionName)
        val transactionDate: TextView = itemView.findViewById(R.id.transactionDate)
        val transactionAmount: TextView = itemView.findViewById(R.id.transactionAmount)
    }
}