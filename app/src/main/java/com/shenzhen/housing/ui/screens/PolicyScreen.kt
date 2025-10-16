package com.shenzhen.housing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shenzhen.housing.R
import com.shenzhen.housing.ui.components.PolicyCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PolicyScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("全部") }
    
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.policy_title)) },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // 搜索栏
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text(stringResource(R.string.policy_search_hint)) },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true
                )
            }
            
            item {
                // 分类筛选
                Text(
                    text = "政策分类",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(getPolicyCategories()) { category ->
                        FilterChip(
                            onClick = { selectedCategory = category },
                            label = { Text(category) },
                            selected = selectedCategory == category
                        )
                    }
                }
            }
            
            item {
                Text(
                    text = "政策列表",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(getPolicyItems()) { policy ->
                PolicyCard(
                    title = policy.title,
                    content = policy.content,
                    category = policy.category,
                    publishDate = policy.publishDate,
                    department = policy.department
                )
            }
        }
    }
}

data class PolicyItem(
    val title: String,
    val content: String,
    val category: String,
    val publishDate: String,
    val department: String
)

fun getPolicyCategories(): List<String> = listOf(
    "全部", "保障性住房", "租赁住房", "购买住房", "旧改政策"
)

fun getPolicyItems(): List<PolicyItem> = listOf(
    PolicyItem(
        "深圳市保障性住房管理办法",
        "为规范保障性住房管理，完善住房保障体系，根据相关法律法规，制定本办法。",
        "保障性住房",
        "2024-01-15",
        "深圳市住房和建设局"
    ),
    PolicyItem(
        "关于进一步规范住房租赁市场的通知",
        "为促进住房租赁市场健康发展，维护租赁双方合法权益，现就有关事项通知如下。",
        "租赁住房",
        "2024-01-10",
        "深圳市住房和建设局"
    ),
    PolicyItem(
        "深圳市商品房销售管理办法",
        "为规范商品房销售行为，维护商品房买卖双方合法权益，促进房地产市场健康发展。",
        "购买住房",
        "2024-01-08",
        "深圳市规划和自然资源局"
    ),
    PolicyItem(
        "关于推进城市更新工作的实施意见",
        "为加快推进城市更新工作，改善人居环境，提升城市品质，现提出如下意见。",
        "旧改政策",
        "2024-01-05",
        "深圳市城市更新和土地整备局"
    )
)
