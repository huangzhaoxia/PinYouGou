package com.pinyougou.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.service.BrandService;
import com.pinyougou.vo.PageResult;
import com.pinyougou.vo.Result;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.web.bind.annotation.*;
import sun.awt.SunHints;
import sun.management.snmp.AdaptorBootstrap;

import java.util.List;

@RequestMapping("/brand")
@RestController
public class BrandController {


    @Reference
    private BrandService brandService;

    @GetMapping("findAll")
    public List<TbBrand> findAll(){

       return  brandService.queryAll();
    }

    @GetMapping("testPage")
    public List<TbBrand> testPage(Integer page,Integer rows){

    //return brandService.testPage(page,rows);
    return (List<TbBrand>) brandService.findPage(page,rows);
    }

    /**
     * 分页查询
     * @param page 页号
     * @param rows 页大小
     * @return
     */
    @GetMapping("/findPage")
    public PageResult findPage(@RequestParam(value="page",defaultValue = "1")Integer page,
                               @RequestParam(value="rows",defaultValue = "10")Integer rows){

        return brandService.findPage(page,rows);
    }

    /**
     * 添加品牌
     * @param brand 品牌
     * @return 添加结果
     */
    @PostMapping("/add")
    public Result addBrand(@RequestBody TbBrand brand){
        try{
            brandService.add(brand);

            return Result.ok("新增成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.fail("新增失败");
    }

    /**
     * 根据id查询品牌
     * @param id 品牌id
     * @return 品牌
     */
    @GetMapping("/findOne")
    public  TbBrand findOne(Long id){
        return brandService.findOne(id);
    }


    /**
     * 修改品牌信息
     * @param brand 品牌
     * @return 修改结果
     */
    @PostMapping("/update")
    public Result updateBrand(@RequestBody TbBrand brand){
        try {
            brandService.update(brand);
            System.out.println(brand.getId());
            return Result.ok("修改成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.fail("修改失败");
    }

    /**
     * 批量删除品牌
     * @param ids 品牌id数组
     * @return 删除结果
     */
    @GetMapping("/delete")
    public Result delete(Long[] ids){
        try {
            brandService.deleteByIds(ids);
            return Result.ok("删除成功");
        }catch(Exception e){
            e.printStackTrace();
        }
        return Result.fail("删除失败");
    }


    @PostMapping("/search")
    public PageResult search(@RequestBody TbBrand brand,
                             @RequestParam(value = "page",defaultValue = "1")Integer page,
                             @RequestParam(value = "rows",defaultValue = "10")Integer rows){

        return brandService.search(brand,page,rows);


    }


}
