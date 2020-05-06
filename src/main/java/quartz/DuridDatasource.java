package quartz;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @Description
 * @Author Cain
 * @date 2020/3/21
 */
@Configuration
public class DuridDatasource {

	@Autowired
	private DuridBean druidPrimaryDataSourceConfigProperties;

	@Bean(name="dataSource")
	@Primary
	public DataSource primaryDataSource (){
		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(this.druidPrimaryDataSourceConfigProperties.getUrl());
		datasource.setUsername(this.druidPrimaryDataSourceConfigProperties.getUsername());
		datasource.setPassword(this.druidPrimaryDataSourceConfigProperties.getPassword());
		datasource.setDriverClassName(this.druidPrimaryDataSourceConfigProperties.getDriverClassName());


		datasource.setInitialSize(this.druidPrimaryDataSourceConfigProperties.getInitialSize());
		datasource.setMinIdle(this.druidPrimaryDataSourceConfigProperties.getMinIdle());
		datasource.setMaxActive(this.druidPrimaryDataSourceConfigProperties.getMaxActive());
		datasource.setMaxWait(this.druidPrimaryDataSourceConfigProperties.getMaxWait());
		datasource.setTimeBetweenEvictionRunsMillis(this.druidPrimaryDataSourceConfigProperties.getTimeBetweenEvictionRunsMillis());
		datasource.setMinEvictableIdleTimeMillis(this.druidPrimaryDataSourceConfigProperties.getMinEvictableIdleTimeMillis());
		datasource.setValidationQuery(this.druidPrimaryDataSourceConfigProperties.getValidationQuery());
		datasource.setTestWhileIdle(this.druidPrimaryDataSourceConfigProperties.getTestWhileIdle());
		datasource.setTestOnBorrow(this.druidPrimaryDataSourceConfigProperties.getTestOnBorrow());
		datasource.setTestOnReturn(this.druidPrimaryDataSourceConfigProperties.getTestOnReturn());

		return datasource;
	}
}
