package com.shenzhen.housing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shenzhen.housing.R
import com.shenzhen.housing.ui.components.FeatureCard
import com.shenzhen.housing.ui.components.NewsCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
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
                // 欢迎横幅
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "欢迎使用深圳住房规划",
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "为您提供最新的住房政策、项目信息和申请指南",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            
            item {
                Text(
                    text = "主要功能",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(getFeatureItems()) { feature ->
                        FeatureCard(
                            title = feature.title,
                            description = feature.description,
                            icon = feature.icon
                        )
                    }
                }
            }
            
            item {
                Text(
                    text = "最新资讯",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(getNewsItems()) { news ->
                NewsCard(
                    title = news.title,
                    content = news.content,
                    date = news.date
                )
            }
        }
    }
}

data class FeatureItem(
    val title: String,
    val description: String,
    val icon: String
)

data class NewsItem(
    val title: String,
    val content: String,
    val date: String
)

fun getFeatureItems(): List<FeatureItem> = listOf(
    FeatureItem("政策查询", "查看最新住房政策", "📋"),
    FeatureItem("住房项目", "浏览住房项目信息", "🏠"),
    FeatureItem("申请指南", "了解申请流程", "📝"),
    FeatureItem("地图查看", "查看项目位置", "🗺️")
)

fun getNewsItems(): List<NewsItem> = listOf(
    NewsItem(
        "深圳市发布2024年住房保障计划",
        "计划新增保障性住房5万套，重点解决新市民、青年人等群体住房困难问题。",
        "2024-01-15"
    ),
    NewsItem(
        "南山区新一批人才住房开始申请",
        "提供1000套人才住房，面向符合条件的各类人才开放申请。",
        "2024-01-10"
    ),
    NewsItem(
        "福田区旧改项目取得新进展",
        "多个旧改项目完成拆迁，即将进入建设阶段。",
        "2024-01-08"
    )
)
