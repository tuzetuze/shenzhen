package com.shenzhen.housing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shenzhen.housing.R
import com.shenzhen.housing.ui.components.MapProjectCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("全部") }
    
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.map_title)) },
                actions = {
                    IconButton(onClick = { /* 定位到当前位置 */ }) {
                        Icon(Icons.Default.MyLocation, contentDescription = "我的位置")
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 搜索和筛选区域
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 搜索栏
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("搜索项目位置") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                // 筛选选项
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(getMapFilters()) { filter ->
                        FilterChip(
                            onClick = { selectedFilter = filter },
                            label = { Text(filter) },
                            selected = selectedFilter == filter
                        )
                    }
                }
            }
            
            // 地图区域（这里用占位符代替实际地图）
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(48.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "地图视图",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "显示住房项目位置",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // 附近项目列表
            Text(
                text = "附近项目",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(getNearbyProjects()) { project ->
                    MapProjectCard(
                        name = project.name,
                        location = project.location,
                        distance = project.distance,
                        type = project.type,
                        status = project.status
                    )
                }
            }
        }
    }
}

data class NearbyProject(
    val name: String,
    val location: String,
    val distance: String,
    val type: String,
    val status: String
)

fun getMapFilters(): List<String> = listOf(
    "全部", "保障性住房", "商品房", "租赁住房", "人才住房"
)

fun getNearbyProjects(): List<NearbyProject> = listOf(
    NearbyProject(
        "南山人才公寓",
        "南山区科技园南区",
        "500米",
        "人才住房",
        "已完工"
    ),
    NearbyProject(
        "福田保障房项目",
        "福田区中心区",
        "1.2公里",
        "保障性住房",
        "建设中"
    ),
    NearbyProject(
        "宝安商品房项目",
        "宝安区新安街道",
        "2.5公里",
        "商品房",
        "已完工"
    ),
    NearbyProject(
        "龙岗租赁住房",
        "龙岗区龙城街道",
        "3.8公里",
        "租赁住房",
        "已完工"
    ),
    NearbyProject(
        "盐田人才住房",
        "盐田区沙头角",
        "5.2公里",
        "人才住房",
        "规划中"
    )
)
