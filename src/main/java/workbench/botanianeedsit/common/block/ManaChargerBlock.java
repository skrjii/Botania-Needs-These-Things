/**
 * This class was created by Workbench
 * File created at [Jan 10, 2019, 15:24 UTC + 7]
 */
package workbench.botanianeedsit.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import vazkii.botania.api.mana.IManaPool;
import workbench.botanianeedsit.common.tile.ManaChargerTile;

import javax.annotation.Nullable;

import java.util.Random;

public class ManaChargerBlock extends Block {
    private static final VoxelShape SHAPE = VoxelShapes.create((1f/8) + (1f/32), (1f/16) + (1f/32), (1f/8) + (1f/32), 1 - (1f/8) - (1f/32), (1f/16) + (1f/32) + (1f/8), 1 - (1f/8) - (1f/32));

    public ManaChargerBlock() {
        super(
                Properties.create(Material.WOOD).
                        sound(SoundType.WOOD).
                        harvestTool(ToolType.AXE).
                        hardnessAndResistance(1)
        );
    }

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) return true;

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof ManaChargerTile)
            return ((ManaChargerTile) tileEntity).handleClick(player, handIn);
        else
            return true;

    }

    @Override
    public void tick(BlockState state, World worldIn, BlockPos pos, Random random) {
        if (!isValidPosition(state, worldIn, pos))
            worldIn.destroyBlock(pos, true);
    }

    @Override
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        }

        return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return worldIn.getTileEntity(pos.down()) instanceof IManaPool;
    }

    @Override
    public boolean hasComparatorInputOverride(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos) {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof ManaChargerTile) {
                return ((ManaChargerTile) tileEntity).getComparatorOutput();
            }
        }
        return 0;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ManaChargerTile();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
