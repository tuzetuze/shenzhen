package com.shenzhen.housing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shenzhen.housing.R
import com.shenzhen.housing.ui.components.ProjectCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectsScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("全部") }
    var selectedDistrict by remember { mutableStateOf("全部") }
    var showFilterDialog by remember { mutableStateOf(false) }
    
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.projects_title)) },
                actions = {
                    IconButton(onClick = { showFilterDialog = true }) {
                        Icon(Icons.Default.FilterList, contentDescription = "筛选")
                    }
                },
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
                    placeholder = { Text("搜索住房项目") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    singleLine = true
                )
            }
            
            item {
                // 类型筛选
                Text(
                    text = "项目类型",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(getProjectTypes()) { type ->
                        FilterChip(
                            onClick = { selectedType = type },
                            label = { Text(type) },
                            selected = selectedType == type
                        )
                    }
                }
            }
            
            item {
                // 区域筛选
                Text(
                    text = "所在区域",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            item {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(getDistricts()) { district ->
                        FilterChip(
                            onClick = { selectedDistrict = district },
                            label = { Text(district) },
                            selected = selectedDistrict == district
                        )
                    }
                }
            }
            
            item {
                Text(
                    text = "项目列表",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            items(getProjectItems()) { project ->
                ProjectCard(
                    name = project.name,
                    location = project.location,
                    type = project.type,
                    priceRange = project.priceRange,
                    areaRange = project.areaRange,
                    status = project.status,
                    totalUnits = project.totalUnits,
                    availableUnits = project.availableUnits
                )
            }
        }
    }
    
    // 筛选对话框
    if (showFilterDialog) {
        FilterDialog(
            onDismiss = { showFilterDialog = false },
            onApply = { type, district ->
                selectedType = type
                selectedDistrict = district
                showFilterDialog = false
            }
        )
    }
}

@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    onApply: (String, String) -> Unit
) {
    var selectedType by remember { mutableStateOf("全部") }
    var selectedDistrict by remember { mutableStateOf("全部") }
    
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("筛选条件") },
        text = {
            Column {
                Text("项目类型")
                LazyRow {
                    items(getProjectTypes()) { type ->
                        FilterChip(
                            onClick = { selectedType = type },
                            label = { Text(type) },
                            selected = selectedType == type
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("所在区域")
                LazyRow {
                    items(getDistricts()) { district ->
                        FilterChip(
                            onClick = { selectedDistrict = district },
                            label = { Text(district) },
                            selected = selectedDistrict == district
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { onApply(selectedType, selectedDistrict) }) {
                Text("应用")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("取消")
            }
        }
    )
}

data class ProjectItem(
    val name: String,
    val location: String,
    val type: String,
    val priceRange: String,
    val areaRange: String,
    val status: String,
    val totalUnits: Int,
    val availableUnits: Int
)

fun getProjectTypes(): List<String> = listOf(
    "全部", "保障性住房", "商品房", "租赁住房", "人才住房"
)

fun getDistricts(): List<String> = listOf(
    "全部", "福田区", "罗湖区", "南山区", "宝安区", "龙岗区", "盐田区", "龙华区", "坪山区", "光明区", "大鹏新区"
)

fun getProjectItems(): List<ProjectItem> = listOf(
    ProjectItem(
        "南山人才公寓",
        "南山区科技园",
        "人才住房",
        "8000-12000元/月",
        "60-120㎡",
        "已完工",
        500,
        120
    ),
    ProjectItem(
        "福田保障房项目",
        "福田区中心区",
        "保障性住房",
        "3000-5000元/月",
        "40-80㎡",
        "建设中",
        800,
        0
    ),
    ProjectItem(
        "宝安商品房项目",
        "宝安区新安街道",
        "商品房",
        "50000-80000元/㎡",
        "80-150㎡",
        "已完工",
        300,
        50
    ),
    ProjectItem(
        "龙岗租赁住房",
        "龙岗区龙城街道",
        "租赁住房",
        "2000-4000元/月",
        "30-60㎡",
        "已完工",
        1000,
        200
    )
)
